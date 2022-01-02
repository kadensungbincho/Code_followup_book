package ch1_safty.item9

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun countCharactersInFile(path: String): Int {
    val reader = BufferedReader(FileReader(path))
    try {
        return reader.lineSequence().sumBy { it.length }
    } finally {
        reader.close()
    }
}

fun countCharactersInFile2(path: String): Int {
    val reader = BufferedReader(FileReader(path))

    reader.use {
        return reader.lineSequence().sumBy { it.length }
    }

    /*
        or
        BufferedReader(FileReader(path)).use { reader ->
            return reader.lineSequence().sumBy { it.length }
        }
     */
}

fun countCharactersInFile3(path: String): Int {
    File(path).useLines { lines ->
        return lines.sumBy { it.length }
    }
}

fun countCharactersInFile4(path: String): Int =
    File(path).useLines { lines ->
        lines.sumBy { it.length }
    }
