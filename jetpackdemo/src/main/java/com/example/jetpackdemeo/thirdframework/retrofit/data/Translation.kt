package com.example.jetpackdemeo.thirdframework.retrofit.data

class Translation {
    var code: Int = 0
    var msg: String? = null
    var data: Data? = null

    class Data {
        var from: String? = null
        var to: String? = null
        var trans_result: Array<TransResult?>? = null
        var Author: Author? = null

        override fun toString(): String {
            return "{from: $from, \n" +
                    "to: $to, \n" +
                    "trans_result: ${trans_result.toString()}, \n" +
                    "Author:$Author}"
        }
    }

    class TransResult {
        var src: String? = null
        var dst: String? = null

        override fun toString(): String {
            return "{src: $src, " +
                    "dst: $dst}"
        }
    }

    class Author {
        var name: String? = null
        var desc: String? = null

        override fun toString(): String {
            return "{src: $name, \n" +
                    "dst: $desc}\n"
        }
    }

    override fun toString(): String {
        return "{code: $code, \n" +
                "msg: $msg, \n" +
                "data:${data.toString()}}\n"
    }
}