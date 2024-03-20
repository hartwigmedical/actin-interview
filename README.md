## ACTIN-interview

### Treatment matching

Every eligibility criterion evaluates to one of the following options:

| Evaluation      | Description                                                                                                                              |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------|
| PASS            | The patient complies with the inclusion or exclusion criterion.                                                                          |
| WARN            | The patient may or may not comply with inclusion or exclusion criterion. A manual evaluation is required.                                |
| FAIL            | The patient does not comply with the inclusion or exclusion criterion.                                                                   |
| UNDETERMINED    | The data that is required to evaluate the inclusion or exclusion criterion is unavailable.                                               |
| NOT_EVALUATED   | The evaluation of the inclusion or exclusion criterion is skipped and can be assumed to be irrelevant for determining trial eligibility. |
| NOT_IMPLEMENTED | No algo has been implemented yet for this criterion.                                                                                     |

#### Evaluation feedback

Every criterion algorithm provides human-readable feedback ('messages') about its evaluation, so that a human can easily and quickly
understand which
evaluation has been done and why the outcome of the evaluation (`PASS`,`WARN`, `FAIL`, `UNDETERMINED` or `NOT_EVALUATED`) is as it is.

#### Treatment eligibility

The following rules are available:

##### Rules related to tumor and lesion localization

| Rule                                                                    | When does a patient pass evaluation?                                                                                                                                                                                                                                                                                                                                                                        | Note                                                                                                                                                                                                                                                                                                                                             |
|-------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| HAS_EXTRACRANIAL_METASTASES                                             | Tumor details > has lesions other than (belonging to category) brain                                                                                                                                                                                                                                                                                                                                        |                                                                                                                                                                                                                                                                                                                                                  |
