CaseBasedDecisionTheory_simulator
=================================

Instructions to run in Eclipse:

1. Import the five bundle projects in the repository into Eclipse.
2. Create an OSGi run configuration. 
   In the configuration:
	2.1 If you are running on an x86_64 machine, select to run all bundles, including ProcessingWrapper.win32.x86_64, but excluding ProcessingWrapp.win32.x86. If you run on an x86 machine, vice versa.
	2.2 Select to add all required bundles.
3. Run the application with the created run configuration.