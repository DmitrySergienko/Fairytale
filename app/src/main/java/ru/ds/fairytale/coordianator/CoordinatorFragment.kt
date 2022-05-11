package ru.ds.fairytale.coordianator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import coil.load
import ru.ds.fairytale.databinding.FragmentCoordinatorBinding
import ru.ds.fairytale.viewModel.DataModel



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
        //загружаем картинку
        //binding.mainBackdrop.load("https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/test%2Fic_fisherman.png?alt=media&token=df7301c0-8934-4b71-bed0-d2a0c29a8a18")
        //загружаем текст из сообщения firebase
        dataModel.imageMessage.observe(activity as LifecycleOwner) {
            val imageFromServer = it
            binding.mainBackdrop.load(imageFromServer)
        }
        dataModel.titleMessage.observe(activity as LifecycleOwner) {
            binding.textView.text = it
        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }
}