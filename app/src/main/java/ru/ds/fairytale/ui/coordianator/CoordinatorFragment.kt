package ru.ds.fairytale.ui.coordianator

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import coil.load
import io.reactivex.exceptions.Exceptions
import ru.ds.fairytale.databinding.FragmentCoordinatorBinding
import ru.ds.fairytale.viewModel.DataModel
import java.net.URL
import java.nio.charset.Charset


class CoordinatorFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()


    private var _binding: FragmentCoordinatorBinding? = null
    val binding: FragmentCoordinatorBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // прописываем behavior через код
        val behavior = ButtonBehaviorMyStyle(requireContext())
        (binding.coordinatorButton.getLayoutParams() as CoordinatorLayout.LayoutParams).behavior =
            behavior

        //загружаем текст из сообщения firebase
        dataModel.imageMessage.observe(activity as LifecycleOwner) {
            val imageFromServer = it
            binding.mainBackdrop.load(imageFromServer)
        }
        dataModel.messageMessage.observe(activity as LifecycleOwner) {

            val handler = Handler()
            Thread {
                if (it.isNullOrBlank()) {
                    handler.post { binding.textView.text = "no data from server" }
                } else {
                    handler.post {
                        val message = it
                        binding.textView.text = URL(message).readText(Charset.forName("UTF-8"))
                    }
                }
            }.start()


        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }
}


