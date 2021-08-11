package moe.nikolay.chucknorisjokes.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import moe.nikolay.chucknorisjokes.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private lateinit var notificationsViewModel: WebViewViewModel
    private var _binding: FragmentWebViewBinding? = null
    private val webClient = WebChromeClient()
    private val saveWebStateName = "webState"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            val bundle = Bundle()
            binding.webView.saveState(bundle)
            binding.webView.restoreState(savedInstanceState.getBundle(saveWebStateName) ?: bundle)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(WebViewViewModel::class.java)
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("http://www.icndb.com/api/")
        binding.webView.webChromeClient = webClient

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        binding.webView.saveState(bundle)
        outState.putBundle(saveWebStateName, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}