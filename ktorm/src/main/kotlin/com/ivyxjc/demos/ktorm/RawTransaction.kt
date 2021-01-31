package com.ivyxjc.demos.ktorm

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDateTime


interface RawTransaction : Entity<RawTransaction> {
    companion object : Entity.Factory<RawTransaction>()

    val guid: Long

    var sourceId: Long?

    var rawRecord: String?

    var sequence: Long

    var version: Int

    var createdAt: LocalDateTime

    var createdBy: String

    var createdFrom: String

    var updatedAt: LocalDateTime?

    var updatedBy: String

    var updatedFrom: String

}

object RawTransactionDO : Table<RawTransaction>("raw_transaction") {
    val guid = long("guid").primaryKey().bindTo { it.guid }
    val sourceId = long("source_id").bindTo { it.sourceId }
    val rawRecord = text("raw_record").bindTo { it.rawRecord }
    val sequence = long("sequence").bindTo { it.sequence }
    val version = int("version").bindTo { it.version }
    val createdAt = datetime("created_at").bindTo { it.createdAt }
    val createdBy = varchar("created_by").bindTo { it.createdBy }
    val createdFrom = varchar("created_from").bindTo { it.createdFrom }
    val updatedAt = datetime("updated_at").bindTo { it.updatedAt }
    val updatedBy = varchar("updated_by").bindTo { it.updatedBy }
    val updatedFrom = varchar("updated_from").bindTo { it.updatedFrom }


}
