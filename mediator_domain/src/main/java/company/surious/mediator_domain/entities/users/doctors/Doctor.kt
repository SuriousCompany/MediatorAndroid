package company.surious.mediator_domain.entities.users.doctors

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.users.SignedUser
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils
import company.surious.mediator_domain.entities.utils.isInnerObjectChanged
import company.surious.mediator_domain.entities.utils.setAll
import company.surious.mediator_domain.entities.utils.updateInnerObject

class Doctor(
    override var id: Long = -1,
    override var googleAuthId: String = "",
    override var displayName: String = "",
    override var email: String = "",
    override var familyName: String = "",
    override var givenName: String = "",
    override var photoUrl: String? = null,
    override var blocked: Boolean = false,
    var category: String = "",
    var specialization: Specialization = Specialization(),
    var education: String = "",
    val workExperience: ArrayList<WorkExperience> = ArrayList(),
    var type: DoctorType = DoctorType.SELF_EMPLOYED,
    var rating: Float? = 0f,
    var hospital: Hospital? = null,
    var patientsReceptionSite: PatientsReceptionSite? = null,
    override var displayNameRu: String? = null,
    override var displayNameUa: String? = null,
    override var familyNameRu: String? = null,
    override var familyNameUa: String? = null,
    override var givenNameRu: String? = null,
    override var givenNameUa: String? = null,
    var educationRu: String? = null,
    var educationUa: String? = null,
    var aboutMe: String? = null,
    var aboutMeRu: String? = null,
    var aboutMeUa: String? = null,
    var floor: Int? = null,
    var department: String? = null,
    var departmentRu: String? = null,
    var departmentUa: String? = null,
    var location: String? = null,
    var locationRu: String? = null,
    var locationUa: String? = null,
    var scientificAndPracticalActivity: String? = null,
    var scientificAndPracticalActivityRu: String? = null,
    var scientificAndPracticalActivityUa: String? = null
) : SignedUser(
    id,
    googleAuthId,
    email,
    displayName,
    displayNameRu,
    displayNameUa,
    familyNameRu,
    familyNameUa,
    givenName,
    givenNameRu,
    givenNameUa,
    familyName,
    photoUrl,
    blocked
), UpdatableEntity<Doctor>, Parcelable {

    override fun isChanged(anotherVersion: Doctor): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return googleAuthId != anotherVersion.googleAuthId
                || email != anotherVersion.email
                || displayName != anotherVersion.displayName
                || familyName != anotherVersion.familyName
                || givenName != anotherVersion.givenName
                || category != anotherVersion.category
                || specialization.isChanged(anotherVersion.specialization)
                || education != anotherVersion.education
                ||
                UpdatableEntityUtils.isListContentChanged(
                    workExperience,
                    anotherVersion.workExperience
                )
                || type != anotherVersion.type
                || rating != anotherVersion.rating
                || isInnerObjectChanged(anotherVersion.hospital, hospital)
                || isInnerObjectChanged(anotherVersion.patientsReceptionSite, patientsReceptionSite)
                || displayNameRu != anotherVersion.displayNameRu
                || displayNameUa != anotherVersion.displayNameUa
                || familyNameRu != anotherVersion.familyNameRu
                || familyNameUa != anotherVersion.familyNameUa
                || givenNameRu != anotherVersion.givenNameRu
                || givenNameUa != anotherVersion.givenNameUa
                || educationRu != anotherVersion.educationRu
                || educationUa != anotherVersion.educationUa
                || aboutMe != anotherVersion.aboutMe
                || aboutMeRu != anotherVersion.aboutMeRu
                || aboutMeUa != anotherVersion.aboutMeUa
                || floor != anotherVersion.floor
                || department != anotherVersion.department
                || departmentRu != anotherVersion.departmentRu
                || departmentUa != anotherVersion.departmentUa
                || location != anotherVersion.location
                || locationRu != anotherVersion.locationRu
                || locationUa != anotherVersion.locationUa
                || scientificAndPracticalActivity != anotherVersion.scientificAndPracticalActivity
                || scientificAndPracticalActivityRu != anotherVersion.scientificAndPracticalActivityRu
                || scientificAndPracticalActivityUa != anotherVersion.scientificAndPracticalActivityUa
                || blocked != anotherVersion.blocked
    }

    override fun update(anotherVersion: Doctor) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        googleAuthId = anotherVersion.googleAuthId
        email = anotherVersion.email
        displayName = anotherVersion.displayName
        familyName = anotherVersion.familyName
        givenName = anotherVersion.givenName
        category = anotherVersion.category
        specialization.update(anotherVersion.specialization)
        education = anotherVersion.education
        workExperience.setAll(anotherVersion.workExperience)
        type = anotherVersion.type
        rating = anotherVersion.rating
        updateInnerObject(
            anotherVersion.hospital,
            hospital,
            { hospital = null },
            { hospital = anotherVersion.hospital })
        updateInnerObject(
            anotherVersion.patientsReceptionSite,
            patientsReceptionSite,
            { patientsReceptionSite = null },
            { patientsReceptionSite = anotherVersion.patientsReceptionSite })
        displayNameRu = anotherVersion.displayNameRu
        displayNameUa = anotherVersion.displayNameUa
        familyNameRu = anotherVersion.familyNameRu
        familyNameUa = anotherVersion.familyNameUa
        givenNameRu = anotherVersion.givenNameRu
        givenNameUa = anotherVersion.givenNameUa
        educationRu = anotherVersion.educationRu
        educationUa = anotherVersion.educationUa
        aboutMe = anotherVersion.aboutMe
        aboutMeRu = anotherVersion.aboutMeRu
        aboutMeUa = anotherVersion.aboutMeUa
        floor = anotherVersion.floor
        department = anotherVersion.department
        departmentRu = anotherVersion.departmentRu
        departmentUa = anotherVersion.departmentUa
        location = anotherVersion.location
        locationRu = anotherVersion.locationRu
        locationUa = anotherVersion.locationUa
        scientificAndPracticalActivity = anotherVersion.scientificAndPracticalActivity
        scientificAndPracticalActivityRu = anotherVersion.scientificAndPracticalActivityRu
        scientificAndPracticalActivityUa = anotherVersion.scientificAndPracticalActivityUa
        blocked = anotherVersion.blocked
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Doctor

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString(),
        1 == source.readInt(),
        source.readString()!!,
        source.readParcelable<Specialization>(Specialization::class.java.classLoader)!!,
        source.readString()!!,
        source.createTypedArrayList(WorkExperience.CREATOR)!!,
        DoctorType.values()[source.readInt()],
        source.readValue(Float::class.java.classLoader) as Float?,
        source.readParcelable<Hospital>(Hospital::class.java.classLoader),
        source.readParcelable<PatientsReceptionSite>(PatientsReceptionSite::class.java.classLoader),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(googleAuthId)
        writeString(displayName)
        writeString(email)
        writeString(familyName)
        writeString(givenName)
        writeString(photoUrl)
        writeInt((if (blocked) 1 else 0))
        writeString(category)
        writeParcelable(specialization, 0)
        writeString(education)
        writeTypedList(workExperience)
        writeInt(type.ordinal)
        writeValue(rating)
        writeParcelable(hospital, 0)
        writeParcelable(patientsReceptionSite, 0)
        writeString(displayNameRu)
        writeString(displayNameUa)
        writeString(familyNameRu)
        writeString(familyNameUa)
        writeString(givenNameRu)
        writeString(givenNameUa)
        writeString(educationRu)
        writeString(educationUa)
        writeString(aboutMe)
        writeString(aboutMeRu)
        writeString(aboutMeUa)
        writeValue(floor)
        writeString(department)
        writeString(departmentRu)
        writeString(departmentUa)
        writeString(location)
        writeString(locationRu)
        writeString(locationUa)
        writeString(scientificAndPracticalActivity)
        writeString(scientificAndPracticalActivityRu)
        writeString(scientificAndPracticalActivityUa)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Doctor> = object : Parcelable.Creator<Doctor> {
            override fun createFromParcel(source: Parcel): Doctor = Doctor(source)
            override fun newArray(size: Int): Array<Doctor?> = arrayOfNulls(size)
        }
    }
}