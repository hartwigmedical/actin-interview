## ACTIN-interview

### Treatment matching

Every treatment defined in the treatment database is evaluated independently.

In case a treatment is a trial, all relevant inclusion and exclusion criteria are evaluated for this trial as well as every criterion
for any specific cohort within this trial.

Every criterion evaluates to one of the following options:

| Evaluation      | Description                                                                                                                              |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------|
| PASS            | The patient complies with the inclusion or exclusion criterion.                                                                          |
| WARN            | The patient may or may not comply with inclusion or exclusion criterion. A manual evaluation is required.                                |
| FAIL            | The patient does not comply with the inclusion or exclusion criterion.                                                                   |
| UNDETERMINED    | The data that is required to evaluate the inclusion or exclusion criterion is unavailable.                                               |
| NOT_EVALUATED   | The evaluation of the inclusion or exclusion criterion is skipped and can be assumed to be irrelevant for determining trial eligibility. |
| NOT_IMPLEMENTED | No algo has been implemented yet for this criterion.                                                                                     |

#### Recoverable status

Each criterion algorithm is configured as 'recoverable' or 'unrecoverable', indicating whether the outcome of the criterion evaluation
could potentially be recovered in case of a `FAIL`. For example, lab values may be insufficient at the moment of evaluation, leading
to `FAIL`, but may be sufficient 2 weeks later
when a new lab test has been done. Hence, lab rules can be 'recoverable', whereas a primary tumor location cannot change and primary tumor
location rules are thus 'unrecoverable'.

#### Evaluation feedback

Every criterion algorithm provides human-readable feedback ('messages') about its evaluation, so that a human can easily and quickly
understand which
evaluation has been done and why the outcome of the evaluation (`PASS`,`WARN`, `FAIL`, `UNDETERMINED` or `NOT_EVALUATED`) is as it is.

#### Treatment eligibility

Once all criteria are evaluated, the following algorithm determines whether a patient is potentially eligible for a trial:

1. For every cohort within a trial, the patient is considered potentially eligible for that cohort in case none of the cohort-specific
   criteria evaluated to unrecoverable `FAIL` or `NOT_IMPLEMENTED`.
2. A patient is eligible for a trial in case none of its overall criteria evaluated to unrecoverable `FAIL` or `NOT_IMPLEMENTED` and the
   trial
   either has no cohorts defined or has at least one cohort that is considered potentially eligible.

Note that, following this logic, a patient is only considered potentially eligible for a cohort if both the cohort is considered eligible
_and_ the trial that the cohort is part of is considered eligible.

The following rules are available:

##### Rules related to tumor and lesion localization

| Rule                                                                    | When does a patient pass evaluation?                                                                                                                                                                                                                                                                                                                                                                        | Note                                                                                                                                                                                                                                                                                                                                             |
|-------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| HAS_EXTRACRANIAL_METASTASES                                             | Tumor details > has lesions other than (belonging to category) brain                                                                                                                                                                                                                                                                                                                                        |                                                                                                                                                                                                                                                                                                                                                  |
