In this project you will create the fundamental PhoneBill and PhoneCall classes that you will work with for the duration of the course.

Goals: Extend classes that you did not write and perform more complex command line parsing

The edu.pdx.cs410J package contains two abstract classes, AbstractPhoneBill and AbstractPhoneCall. For this project you will write two concrete classes in your edu.pdx.cs410J.login package: PhoneBill that extends AbstractPhoneBill and PhoneCall that extends AbstractPhoneCall. Each of your classes must implement all of the abstract methods of its superclass.

A PhoneBill has a customer name and consists of multiple PhoneCalls. A PhoneCall is initiated by a person with a given phone number at a given time, is received by a person with a given phone number,and terminates at a given time. For this assignment, all of this data should be modeled with Strings. Additionally, you may ignore the getStartTime and getEndTime methods.

You should also create a Project1 class that contains a main method that parses the command line, creates an PhoneBill and a PhoneCall as specified by the command line, adds the PhoneCall to the PhoneBill, and optionally prints a description of the PhoneCall by invoking its toString method7.

