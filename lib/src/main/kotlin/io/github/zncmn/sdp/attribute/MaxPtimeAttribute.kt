package io.github.zncmn.sdp.attribute

import io.github.zncmn.sdp.SdpParseException

data class MaxPtimeAttribute internal constructor(
    var time: Int
) : SdpAttribute {
    override val field = FIELD_NAME
    override val value: String
        get() = buildString { valueJoinTo(this) }

    override fun toString(): String {
        return buildString { joinTo(this) }
    }

    override fun joinTo(buffer: StringBuilder) {
        buffer.apply {
            append("a=")
            append(field)
            append(':')
            append(time)
            append("\r\n")
        }
    }

    private fun valueJoinTo(buffer: StringBuilder) {
        buffer.append(time)
    }

    companion object {
        internal const val FIELD_NAME = "maxptime"

        @JvmStatic
        fun of(streamId: Int): MaxPtimeAttribute {
            return MaxPtimeAttribute(streamId)
        }

        internal fun parse(value: String): MaxPtimeAttribute {
            val time = value.toIntOrNull() ?: run {
                throw SdpParseException("could not parse: $value as MaxPtimeAttribute")
            }
            return MaxPtimeAttribute(time)
        }
    }
}