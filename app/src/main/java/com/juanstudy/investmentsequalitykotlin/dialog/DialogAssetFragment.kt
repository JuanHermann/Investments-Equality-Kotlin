package com.juanstudy.investmentsequalitykotlin.dialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.juanstudy.investmentsequalitykotlin.databinding.FragmentDialogAssetBinding
import com.juanstudy.investmentsequalitykotlin.models.Asset
import com.juanstudy.investmentsequalitykotlin.viewmodel.MainViewModel

class DialogAssetFragment : Fragment() {

    private lateinit var binding: FragmentDialogAssetBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogAssetBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[(MainViewModel::class.java)]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        binding.etAssetTag.requestFocus()
    }

    private fun setOnClick() {
        binding.buttonCancel.setOnClickListener { dismiss() }
        binding.constraintParent.setOnClickListener { dismiss() }
        binding.etAssetTag.addTextChangedListener(getTextWatcher())
        binding.buttonSave.setOnClickListener {
            viewModel.insert(Asset(binding.etAssetTag.text.toString()))
            dismiss()
        }
    }

    private fun dismiss() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    private fun getTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length < 1 || charSequence.length > 6) {
                    binding.buttonSave.setEnabled(false)
                } else {
                    binding.buttonSave.setEnabled(true)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        }
    }

}