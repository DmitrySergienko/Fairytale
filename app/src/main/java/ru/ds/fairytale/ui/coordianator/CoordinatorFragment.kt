package ru.ds.fairytale.ui.coordianator

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import coil.load
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.ds.fairytale.app
import ru.ds.fairytale.databinding.FragmentCoordinatorBinding
import ru.ds.fairytale.ui.test.FirebaseViewModel
import ru.ds.fairytale.ui.test.ViewModelFactory
import ru.ds.fairytale.viewModel.DataModel
import java.net.URL
import java.nio.charset.Charset


class CoordinatorFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()
    private val viewModel: FirebaseViewModel by viewModels { ViewModelFactory(requireActivity().app.firebaseRep) }

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

        coordinatorButtonBehavior()// прописываем behavior через код
        downloadImageFromFireBase()//download picture from firebase
        downloadTextFromFireBase() //download text from firebase


    }

    private fun imageDefault() {

        val imageDefault =
            "https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/782771327_kartina-po-nomeram.jpg?alt=media&token=a83ca8bb-e86a-4637-a717-83d2be22d190"
        binding.mainBackdrop.load(imageDefault)

    }

    private fun downloadTextFromFireBase() {
        viewModel.onShowData()
        viewModel.repo.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrBlank()) {
                binding.textView.text = "no data from server"
            } else {
                binding.textView.text = it
            }
        })
    }

    private fun downloadImageFromFireBase() {

        dataModel.imageMessage.observe(activity as LifecycleOwner) {
            if (it.isNullOrBlank()) {
                imageDefault()
            } else {
                val imageFromServer = it
                binding.mainBackdrop.load(imageFromServer)
            }

        }
    }

    private fun coordinatorButtonBehavior() {
        val behavior = ButtonBehaviorMyStyle(requireContext())
        (binding.coordinatorButton.getLayoutParams() as CoordinatorLayout.LayoutParams).behavior =
            behavior
    }

    companion object {
        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }
}


