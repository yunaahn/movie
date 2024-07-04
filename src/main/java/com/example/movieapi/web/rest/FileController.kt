package com.example.movieapi.web.rest

import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.nio.file.Files

@RestController
@RequestMapping("/files")
class FileController {

    @Value("\${file.dir}")
    private lateinit var fileDir: String

    @GetMapping("/{filename}")
    fun getFile(@PathVariable filename: String, response: HttpServletResponse) {


        val file = File("$fileDir/$filename")
        if (file.exists()) {
            response.contentType = Files.probeContentType(file.toPath())
            response.setHeader("Content-Disposition", "inline; filename=\"$filename\"")
            response.outputStream.write(file.readBytes())
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
        }
    }
}
