package com.example.newsapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newsapp.Prefs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDashboardBinding
import com.example.newsapp.databinding.FragmentProfileBinding
import com.example.newsapp.ui.board.BoardAdapter

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var prefs: Prefs


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FriendsAdapter(requireContext(),findNavController())
        binding.recyclerViewFriends.adapter = adapter
        saveImage()
        initLauncher()
        saveName()


    }


    private fun saveImage() {
        binding.btnSave.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            launcher.launch(intent)
        }
    }

    private fun initLauncher() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                val image = it.data?.data
                if (image != null) {
                    binding.imageView.setImageURI(image)
                    binding.profileImageView.setImageURI(image)

                }
            }
        }
    }  private fun saveName() {

    binding.etName.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            context?.let { Prefs(it).saveNames(s.toString()) };
            //prefs.saveNames(s.toString());
        }
    })
    binding.etName.setText(context?.let { Prefs(it).getName() })
    //binding.editText.setText(prefs.getName());
}
}
