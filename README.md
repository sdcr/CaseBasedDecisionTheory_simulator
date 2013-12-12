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

### Usage example

1. Run the application.
2. Set the `parameter` values: <br>
    Create two `actor actions`. Set the `outcomes` of the actions to the following:<br>
    - Action A: <br>
        - `probabiliy`: 1; `utility`: 1
    - Action B: <br>
        - `probability`: 0.5; `utility`: 0
        - `probability`: 0.5; `utility`: 4
3. Set the engine `configuration`:
    - Select the algorithm `DFS with tree structure`.
    - Set the number of `expected utility values` to 5.
    - Select all further check boxes.
4. Click on `Start computation`.
5. Once the result is computed, note the computed values in the table `Result analysis`.
6. Click on `Show tree structure`. A animated tree should be displayed. The values for each computed node can be inspected by hovering with the mouse pointer over the respective node.
