package company.surious.mediator_domain.entities.users.doctors

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.Nameable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils
import company.surious.mediator_domain.entities.utils.isInnerObjectChanged
import company.surious.mediator_domain.entities.utils.updateInnerObject

class Specialization(
    override var id: Long = -1,
    override var name: String = "",
    override var nameRu: String? = null,
    override var nameUa: String? = null,
    var parentSpecialization: Specialization? = null,
    var description: String = "",
    var descriptionRu: String = "",
    var descriptionUa: String = ""
) : UpdatableEntity<Specialization>, Nameable, Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Specialization
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun isChanged(anotherVersion: Specialization): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return name != anotherVersion.name
                || nameRu != anotherVersion.nameRu
                || nameUa != anotherVersion.nameUa
                || description != anotherVersion.description
                || descriptionRu != anotherVersion.descriptionRu
                || descriptionUa != anotherVersion.descriptionUa
                || isInnerObjectChanged(anotherVersion.parentSpecialization, parentSpecialization)
    }

    override fun update(anotherVersion: Specialization) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        name = anotherVersion.name
        nameRu = anotherVersion.nameRu
        nameUa = anotherVersion.nameUa
        description = anotherVersion.description
        descriptionRu = anotherVersion.descriptionRu
        descriptionUa = anotherVersion.descriptionUa
        updateInnerObject(
            anotherVersion.parentSpecialization,
            parentSpecialization,
            { parentSpecialization = null },
            { parentSpecialization = anotherVersion.parentSpecialization }
        )
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readString(),
        source.readString(),
        source.readParcelable<Specialization>(Specialization::class.java.classLoader),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(name)
        writeString(nameRu)
        writeString(nameUa)
        writeParcelable(parentSpecialization, 0)
        writeString(description)
        writeString(descriptionRu)
        writeString(descriptionUa)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Specialization> =
            object : Parcelable.Creator<Specialization> {
                override fun createFromParcel(source: Parcel): Specialization =
                    Specialization(source)

                override fun newArray(size: Int): Array<Specialization?> =
                    arrayOfNulls(size)
            }
    }
}