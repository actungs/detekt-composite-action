# Detekt Composite Action

[![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/actungs/detekt-composite-action?style=flat-square)][releases]
[![GitHub](https://img.shields.io/github/license/actungs/detekt-composite-action?style=flat-square)](./LICENSE)

A [GitHub composite action][gh-composite-action] to run [detekt][detekt], a static code analysis for Kotlin.

[gh-composite-action]: https://docs.github.com/en/actions/creating-actions/creating-a-composite-action
[detekt]: https://github.com/detekt/detekt

## Usage

A simple detekt workflow using this action could look like this

```yaml
on: pull_request
jobs:
  detekt:
    name: Run detekt analysis
    runs-on: ubuntu-latest
    steps:
      - name: Checkout Code Base
        uses: actions/checkout@v4

      - name: Run detekt
        id: detekt
        uses: actungs/detekt-composite-action@v1
        with:
          extra_arguments: '--config detekt.yaml --build-upon-default-config'

      - name: Upload report
        uses: actions/upload-artifact@v4
        with:
          name: ${{ steps.detekt.outputs.report }}
          path: ${{ steps.detekt.outputs.report }}
```

## Inputs

These are the inputs, which can be given when using the action.

| Name                     | Description                                                                                                                                                          | Required | Default                     |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|:--------:|-----------------------------|
| `detekt_version`         | Version of detekt to be used. If the default `latest` is used, the action will fetch the latest detekt's release version via GitHub API.                             |          | latest                      |
| `java_version`           | Version of Java to be used to run detekt.                                                                                                                            |          | 24                          |
| `java_distribution`      | Distribution of Java to be used. See [setup-java][setup-java-distributions] for a list of supported distribution.                                                    |          | temurin                     |
| `with_formatting_plugin` | Whether to run detekt with the formatting plugin. The plugin is required if `formatting` is used in your configuration and detekt version >= `1.20.0` is to be used. |          | true                        |
| `extra_arguments`        | Additional arguments to run detekt with. See [detekt cli][detekt-cli] for a list of available arguments.                                                             |          | --build-upon-default-config |
| `output_format`          | The file format to be outputted after the action finished. See also [detekt cli --report option][detekt-cli] for all available values.                               |          | md                          |

[setup-java-distributions]: https://github.com/actions/setup-java#supported-distributions
[detekt-cli]: https://detekt.dev/docs/gettingstarted/cli/

## Outputs

The action outputs one report file (named `report`) in the requested format (default: md),
which can be then used like in the example in the [Usage](#usage) section.

## License

Distributed under the MIT License. See [LICENSE](./LICENSE) for more information.
