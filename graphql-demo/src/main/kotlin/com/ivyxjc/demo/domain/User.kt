package com.ivyxjc.demo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*


@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "sequenceGenerator"
    )
    @SequenceGenerator(name = "sequenceGenerator")
    var id: Long? = null,

    @Column(length = 254, unique = true)
    var email: String? = null,

    @Column(length = 254, unique = true, nullable = false)
    var login: String? = email,

    @Column(
        name = "password_hash",
        length = 60,
        nullable = false
    )
    @JsonIgnore
    var password: String? = null,

    @Column(
        name = "created_by",
        nullable = false,
        length = 50,
        updatable = false
    )
    @JsonIgnore
    var createdBy: String? = null,

    @Column(
        name = "created_date",
        updatable = false
    )
    @JsonIgnore
    var createdDate: Instant? = Instant.now(),

    @LastModifiedBy
    @Column(
        name = "last_modified_by",
        length = 50
    )
    @JsonIgnore
    var lastModifiedBy: String? = null,

    @LastModifiedDate
    @Column(
        name = "last_modified_by",
    )
    @JsonIgnore
    var lastModifiedDate: Instant? = Instant.now()


) {
}
