package flame.transformers

import flame.fields.GenerateStatementOutput
import flame.params.GenerateStatementParams
import kase.Result
import kase.catching

fun GenerateStatementOutput.toParams(customerId: String): Result<GenerateStatementParams> = catching {
    val r = range ?: throw IllegalArgumentException("date range must be provided")
    GenerateStatementParams(
        customerId = customerId,
        start = r.start,
        end = r.end
    )
}