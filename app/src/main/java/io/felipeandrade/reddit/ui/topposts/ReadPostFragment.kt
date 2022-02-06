package io.felipeandrade.reddit.ui.topposts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.felipeandrade.reddit.R
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.databinding.FragmentReadPostBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*


class ReadPostFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by sharedViewModel()
    private val binding by lazy { FragmentReadPostBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = sharedViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.image.setOnClickListener {
            val post = sharedViewModel.openedPost.value ?: return@setOnClickListener
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(post.imageUrl))
            startActivity(browserIntent)
        }

        binding.download.setOnClickListener {
            val imageUrl = sharedViewModel.openedPost.value?.imageUrl ?: return@setOnClickListener
            downloadImage(imageUrl)
        }

        sharedViewModel.openedPost.observe(viewLifecycleOwner) { post ->
            binding.author.text = post.author
            binding.text.text = post.title
            Glide.with(requireActivity())
                .load(post.imageUrl)
                .placeholder(R.drawable.ic_image)
                .into(binding.image)
        }
    }


    private fun downloadImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    val bitmap = (resource as BitmapDrawable).bitmap
                    Toast.makeText(requireContext(), "Saving Image...", Toast.LENGTH_SHORT).show()
                    saveImage(bitmap)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Toast.makeText(
                        requireContext(),
                        "Failed to Download Image! Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun saveImage(image: Bitmap, imageFileName: String = "${UUID.randomUUID()}.png") {
        val storageDir = File(commonDocumentDirPath())
        if (!storageDir.exists()) {
            val successDirCreated = storageDir.mkdir()
            if (!successDirCreated) {
                Toast.makeText(requireActivity(), "Failed to make folder!", Toast.LENGTH_SHORT)
                    .show()
                return
            }
        }

        val imageFile = File(storageDir, imageFileName)
        try {
            val fOut: OutputStream = FileOutputStream(imageFile)
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.close()
            Toast.makeText(requireActivity(), "Image Saved!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), "Error while saving image!", Toast.LENGTH_SHORT)
                .show()
            e.printStackTrace()
        }
    }


    private fun commonDocumentDirPath(): String {
        val folderName = getString(R.string.app_name)
        return (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/$folderName"
        else "${Environment.getExternalStorageDirectory()}/$folderName")
    }

}