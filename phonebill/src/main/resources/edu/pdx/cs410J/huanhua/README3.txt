This is a PhoneBill project that takes customer's name, caller phone number, callee phone number, start time, end time as parameters, then output the result PhoneBill information.

Author: Kanra Su
Project 3: Pretty Printing A Phone Bill

usage: Project3 [options] <args>
	args are (in this order):
		customer 		Person whose phone bill we're modeling
		callerNumber 	Phone number of caller
		calleeNumber 	Phone number of person who was called
		start 			Date and time call began (24-hour time)
		end 			Date and time call ended (24-hour time)
	options are (options may appear in any order):
		-pretty file	Pretty print the phone bill to a text file
						or standard out (file -).
		-textFile file 	Where to read/write the phone bill
		-print 			Prints a description of the new phone call
		-README 		Prints a README for this project and exits
		
	Date and time should be in the format: mm/dd/yyyy hh:mm pm