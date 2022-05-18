package ru.ds.fairytale.ui.test

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.ds.fairytale.app
import ru.ds.fairytale.databinding.FragmentTestBinding
import java.net.URL
import java.nio.charset.Charset


class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    val binding: FragmentTestBinding
        get() = _binding!!

    private val viewModel: FirebaseViewModel by viewModels { ViewModelFactory(requireActivity().app.firebaseRep) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonEnterText.setOnClickListener {
            val handler = Handler()
            Thread {
                val str = getTextFromUrl()
                handler.post {
                    binding.textView.text = str
                }
            }.start()
        }
    }

    fun getTextFromUrl(): String {
        val str =
            URL("https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/The_fisherman_and_gold_fish.txt?alt=media&token=18fd106c-f7a2-4c67-869d-03ceddbe3632")
                        .readText(Charset.forName("UTF-8"))
        return str
    }

    companion object {

        @JvmStatic
        fun newInstance() = TestFragment()


    }
}