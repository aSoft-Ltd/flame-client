@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.utils

import bringer.DownloadOptions
import bringer.Downloader
import klip.Clipboard
import kotlin.js.JsExport

class RemoteLink(
    val url: String,
    private val clipboard: Clipboard,
    private val downloader: Downloader
) {
    fun copyToClipboard() = clipboard.setText(url)
    fun open() = downloader.download(DownloadOptions(url, "file"))
    fun download() = downloader.download(url)
}