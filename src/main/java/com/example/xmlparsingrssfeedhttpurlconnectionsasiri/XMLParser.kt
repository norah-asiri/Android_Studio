package com.example.xmlparsingrssfeedhttpurlconnectionsasiri


import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

data class Song(val title: String?) {
    override fun toString(): String = title!!
}

class XMLParser {
    private val ns: String? = null

    fun parse(inputStream: InputStream): List<Song> {
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readSongsRssFeed(parser)
        }
    }

    private fun readSongsRssFeed(parser: XmlPullParser): List<Song> {
        val songs = mutableListOf<Song>()
        parser.require(XmlPullParser.START_TAG, ns, "rss")

        while (parser.next() != XmlPullParser.END_TAG) {

            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }

            if (parser.name == "channel") {
                parser.require(XmlPullParser.START_TAG, ns, "channel")

                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.name == "item") {
                        parser.require(XmlPullParser.START_TAG, ns, "item")
                        var title: String? = null
                        if (parser.eventType != XmlPullParser.START_TAG) {
                            continue
                        }
                        when (parser.name) {
                           // "item" -> skip(parser)
                            "im: title" -> title = readTitle(parser)
                            else -> skip(parser)
                        }
                    }
                }
                //songs.add(Song(title,name))
            } else {
                skip(parser)
            }
        }
        return songs
    }

    private fun readTitle(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "title")
        val title = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "title")
        return title
    }
/*
    private fun readName(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "im:name")
        val summary = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "im:name")
        return summary
    }

 */

    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}
