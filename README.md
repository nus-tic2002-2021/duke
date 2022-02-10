<img src="https://user-images.githubusercontent.com/88638946/130622078-e049680c-7895-4725-ace0-49869574887c.png" width="50%" align="right">

# Duke

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Demo
![output](https://user-images.githubusercontent.com/88638946/131530452-cbe5ab59-9e43-4c08-affe-f9fdf2c75427.gif)


## Build
Prerequisites: Have `bazel` installed.

To build for local development on macOS:
```bash
# bazel makes a wrapper script around the jar file.
bazel build //:TerminalDuke && ./bazel-bin/TerminalDuke

# or simply
bazel run //:TerminalDuke

# Use bazelisk for M1 Macs (Apple Silicon)
USE_BAZEL_VERSION=ac9353fab161efae4af72e73fbb657a762b3620d bazelisk run //:TerminalDuke

# For QA build, for example, having assertions enabled, build TerminalDukeQA instead
USE_BAZEL_VERSION=ac9353fab161efae4af72e73fbb657a762b3620d bazelisk run //:TerminalDukeQA
```

To build jar file for deployment purposes:
```bash
bazel build //:Duke_deploy.jar

java -jar ./bazel-bin/Duke_deploy.jar
```

## Testing
For unit tests:
```bash
bazel test --test_output=all //...
```
For terminal ui tests (`text-ui-test`):
```bash
cd text-ui-test
./runtest.sh
```

## Code Formatting & Style
Use [google-java-format](https://github.com/google/google-java-format):
```bash
google-java-format --replace **/*.java # executes recursively
```

## Pre-commit Git Hook (Tested on macOS)
`pre-commit` is a python tool that helps to create git pre-commit hook handlers. For this project, it is used to enforce Google Java Style (via `google-java-format`) on new commits.

To setup, ensure you have [`pre-commit`](https://pre-commit.com/#install) installed, then run:
```bash
pre-commit install
```
This only needs to be executed once, so that `pre-commit` can generate the script and write it to `.git/hooks/pre-commit`
Thereafter, it should be executed right before every commit!
