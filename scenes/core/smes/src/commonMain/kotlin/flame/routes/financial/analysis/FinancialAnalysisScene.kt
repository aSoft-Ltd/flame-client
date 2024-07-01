@file:JsExport

package flame.routes.financial.analysis

import cinematic.LazyScene
import flame.SmePresenter
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.analysis.FinancialReportsDto
import flame.forms.financial.analysis.FinancialAnalysisFields
import flame.transformers.reports.toOutput
import flame.transformers.toPresenter
import kase.toLazyState
import koncurrent.FailedLater
import koncurrent.later.finally
import koncurrent.later.zip
import kotlinx.JsExport
import symphony.Confirm
import symphony.Peekaboo
import symphony.toForm

abstract class FinancialAnalysisScene(private val options: SmeSceneOptions<SmeScheme>) : LazyScene<SmePresenter>() {
    protected open val api = options.api

    val form = Peekaboo { report: FinancialReportsDto? ->
        val fields = FinancialAnalysisFields()
        fields.toForm(
            heading = "Financial analysis form",
            details = "Financial analysis form",
            logger = options.logger
        ) {
            onSubmit {
                val sme = ui.value.data?.src ?: return@onSubmit FailedLater("Couldn't obtain reports")
                val output = fields.toOutput().getOrThrow()
                val updated = sme.copy(reports = sme.reports + output)
                options.auth.session().zip(api.update(updated)) { (session, sme) ->
                    sme.toPresenter(options.toAttachmentOptions(session))
                }.finally {
                    ui.value = it.toLazyState()
                }
            }
        }
    }

    val confirm = Confirm { report: FinancialReportsDto ->
        heading = "Remove Financial Reports"
        details = "Are you sure you want to remove reports from ${report.date.start}?"
        onConfirm {
            val sme = ui.value.data?.src ?: return@onConfirm FailedLater("Couldn't obtain reports")
            val updated = sme.copy(reports = sme.reports - report)
            options.auth.session().zip(api.update(updated)) { (session, sme) ->
                sme.toPresenter(options.toAttachmentOptions(session))
            }.finally {
                ui.value = it.toLazyState()
            }
        }
    }

    fun add() = form.show(null)

    fun remove(report: FinancialReportsDto) = confirm.show(report)
}