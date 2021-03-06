package edu.pdx.cs410J.huanhua;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import edu.pdx.cs410J.PhoneBillDumper;

/**
 * A PrettyPrinter creates a nicely formatted textual presentation of the
 * phone calls in a phone bill
 * <p>
 * It pretty print the duration of each phone call in minutes.
 *
 * @author KANRA
 */
public class PrettyPrinter implements PhoneBillDumper<PhoneBill> {

    private String filename;

    private final static String DATE_PATTERN = "EEE, d MMM yyyy hh:mm a";

    /**
     * create a PrettyPrinter with a filename
     *
     * @param filename
     * @throws IllegalArgumentException
     */
    public PrettyPrinter(String filename) throws IllegalArgumentException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename is invalid in Pretty Printer");
        }

        this.filename = filename;
    }

    /**
     * Pretty print the phone bill to a text file or standard out
     *
     * @param bill
     *         PhoneBill
     * @throws IOException
     */
    @Override
    public void dump(PhoneBill bill) throws IOException {
        File file = new File(this.filename);
        //		file.deleteOnExit();

        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String output = constructPrettyOutput(bill, null, null);

        pw.println(output);

        pw.close();
    }

    /**
     * Construct a pretty output String for a PhoneBill
     *
     * @param bill
     *         PhoneBill
     * @return output string
     */
    public static String constructPrettyOutput(PhoneBill bill, Date startLimit, Date endLimit) {
        String re = "";
        String callRe = "";
        int count = 0;
        Collection<PhoneCall> calls = bill.getPhoneCalls();
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);

        re += "Customer: " + bill.getCustomer() + "\n\n";

        for (PhoneCall call : calls) {
            Date start = call.getStartTime();
            Date end = call.getEndTime();

            // filter by start and end Date if provide
            if ((startLimit != null && endLimit != null && start.compareTo(startLimit) >= 0 && start.compareTo(endLimit) <= 0) || (startLimit == null && endLimit == null)) {
                int duration = getDurationInMinutes(start, end);
                String startString = dateFormat.format(start);
                String endString = dateFormat.format(end);

                callRe += "Called from " + call.getCaller() + " to " + call.getCallee() + "\n"
                        + "         +- " + startString + "\n"
                        + "         |  " + duration + " minutes" + "\n"
                        + "         +- " + endString + "\n\n";
                ++count;
            }
        }

        if (startLimit != null && endLimit != null) {
            String startString = dateFormat.format(startLimit);
            String endString = dateFormat.format(endLimit);

            re += "Range: " + startString + " ~ " + endString + "\n\n";
        }

        re += "Total calls: " + count + "\n\n";
        re += callRe + "\n";

        return re;
    }

    /**
     * Get the minute difference of two date in Int
     *
     * @param start
     * @param end
     * @return minute difference
     */
    public static int getDurationInMinutes(Date start, Date end) {

        int minuteDiff = (int) ((end.getTime() - start.getTime()) / (1000 * 60));

        return minuteDiff;
    }

}
