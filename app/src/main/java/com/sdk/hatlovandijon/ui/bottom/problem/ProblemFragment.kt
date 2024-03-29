package com.sdk.hatlovandijon.ui.bottom.problem

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.sdk.domain.model.search.SearchData
import com.sdk.domain.model.upload.AddAppealRequest
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentProblemBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.ui.bottom.add.AddData
import com.sdk.hatlovandijon.util.Constants.TAG
import com.sdk.hatlovandijon.util.viewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File


class ProblemFragment : BaseFragment(R.layout.fragment_problem) {
    private val binding by viewBinding { FragmentProblemBinding.bind(it) }
    private val imageAdapter by lazy { ImageAdapter() }
    private var addData: AddData? = null
    private val searchList = mutableListOf<SearchData>()
    private val viewModel: ProblemViewModel by viewModels()
    private var job: Job? = null
    private var typeInt: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addData = arguments?.getParcelable("add_data")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.rv.apply {
            adapter = imageAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.imagePicker.click {
            binding.prImage.isVisible = true
            binding.imgUpload.isVisible = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                pickImage10Higher()
            } else {
                requestMultiplePermissions()
            }
        }
        setupEditTexts()
        searchAutoComplete()
        binding.btnSave.setOnClickListener {
            uploadAppeal()
        }
        observeState()
        lifecycleScope.launch {
            viewModel.searchData.collect {
                searchList.addAll(it)
            }
        }
    }

    private fun uploadAppeal() {
        val comment = binding.etDesc.text.toString().trim()
        val type = binding.tvSelect.text.toString()
        addData?.let { data ->
            val appealRequest = AddAppealRequest(
                data.address,
                data.ownerHomeName,
                data.ownerHomeYear,
                data.ownerHomeGender,
                data.ownerHomePhone,
                data.ownerAsSpeaker,
                data.speakerName,
                data.speakerYear,
                data.speakerGender,
                data.speakerPhone,
                typeInt,
                comment,
                imageAdapter.uriList.map {
                    File(imageFile(it))
                }
            )
            if (type.isNotBlank() && comment.isNotBlank() && imageAdapter.uriList.isNotEmpty()) {
                viewModel.onEvent(ProblemEvent.OnSaveAppeal(appealRequest))
            } else {
                snack(getString(R.string.enter_correct_data), false)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when(it) {
                    is ProblemState.Idle -> Unit
                    is ProblemState.Loading -> {
                        binding.pr.isVisible = true
                        binding.btnSave.isVisible = false
                    }
                    is ProblemState.Error -> {
                        binding.pr.isVisible = false
                        binding.btnSave.isVisible = true
                        snack(getString(R.string.error_occ), false)
                    }
                    is ProblemState.SuccessUpdate -> Unit
                    is ProblemState.Success -> {
                        binding.pr.isVisible = false
                        binding.btnSave.isVisible = true
                        snack(getString(R.string.uploaded), true)
                        findNavController().navigate(R.id.action_problemFragment_to_mainFragment)
                    }
                }
            }
        }
    }

    private fun pickImage10Higher() {
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 4)
        intent.type = "image/*"
        startActivityForResult(intent, 55)
    }

    private fun setupImagePicker() {
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(4)
            .setMinCount(1)
            .setActionBarColor(ContextCompat.getColor(requireContext(), R.color.blue))
            .setAllViewTitle(getString(R.string.all_images))
            .setActionBarTitle(getString(R.string.image_pick))
            .textOnImagesSelectionLimitReached(getString(R.string.many))
            .textOnNothingSelected(getString(R.string.less))
            .startAlbumWithActivityResultCallback(resultLauncher)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                if (it.resultCode == RESULT_OK) {
                    val images =
                        it.data?.getParcelableArrayListExtra<Uri>(FishBun.INTENT_PATH)
                            ?: arrayListOf()
                    imageAdapter.addList(images)
                    binding.imgUpload.isVisible = false
                    binding.rv.isVisible = true
                    binding.prImage.isVisible = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun requestMultiplePermissions() {
        Dexter.withContext(requireContext())
            .withPermissions(
                listOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        setupImagePicker()
                    } else if (!report.areAllPermissionsGranted()) {
                        binding.imgUpload.isVisible = true
                        binding.rv.isVisible = false
                        binding.prImage.isVisible = false
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?,
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 55) {
            var mediaCount = 0
            val list = mutableListOf<Uri>()
            while (mediaCount < data?.clipData!!.itemCount) {
                val uri = data.clipData!!.getItemAt(mediaCount).uri
                mediaCount++
                list.add(uri)
            }
            imageAdapter.addList(list)
            binding.imgUpload.isVisible = false
            binding.rv.isVisible = true
            binding.prImage.isVisible = false
        }
    }

    private fun setupEditTexts() {
        binding.apply {
            etDesc.sutUpInput(binding.tvDesc)
        }
    }
    private fun searchAutoComplete() {
        binding.tvSelect.click {
            showSearchableFragment(searchList) {
                typeInt = it.id
                binding.tvSelect.text = it.name
            }
        }
    }
    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
    }
}