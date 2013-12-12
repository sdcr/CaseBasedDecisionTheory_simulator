CaseBasedDecisionTheory_simulator
=================================

### How to run in Eclipse:

1. Import the five projects of the repository in Eclipse.

2. Open the `plugin.xml` file in the project `CBDT` and configure its dependencies for your machine:<br>
    - On an x86_64 machine:<br>
      Add the plug-in `ProcessingWrapper.win32.x86_64` as an dependency, but not the plug-in `ProcessingWrapp.win32.x86`.
    - On an x86 machine:<br>
      Add the plug-in `ProcessingWrapp.win32.x86` as an dependency, but not the plug-in `ProcessingWrapper.win32.x86_64`.

3. Create an OSGi run configuration:<br>
    In the configuration, deselect all bundles, except `CBDT` and `SimulationCore`. Select to add all required bundles.
4. Run the application with the created run configuration.
