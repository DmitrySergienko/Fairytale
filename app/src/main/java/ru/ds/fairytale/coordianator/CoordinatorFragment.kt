package ru.ds.fairytale.coordianator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import ru.ds.fairytale.databinding.FragmentCoordinatorBinding


class CoordinatorFragment : Fragment() {


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
        (binding.myButton.getLayoutParams() as CoordinatorLayout.LayoutParams).behavior = behavior
        binding.mainBackdrop.setImageResource(downloadImage())

    }

    private fun downloadImage():Int {

        val image =startActivity(Intent(Intent.ACTION_VIEW).apply {
            data =
                Uri.parse(
                    //"https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/test%2Fic_fisherman.png?alt=media&token=df7301c0-8934-4b71-bed0-d2a0c29a8a18"${binding.inputEditText.text.toString()}")
                    "https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/test%2Fic_fisherman.png?alt=media&token=df7301c0-8934-4b71-bed0-d2a0c29a8a18")
        })
        return image.toString().toInt()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }
}