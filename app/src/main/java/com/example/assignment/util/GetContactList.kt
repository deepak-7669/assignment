package com.example.assignment.util





class GetContactList {
/*
    fun checkContacts(ctx: Context): ArrayList<SimpleContact>? {
        val distinctLocations = getContactInOtherWay(ctx)!!
        return distinctLocations
    }

    fun getSortedList(ctx: Context): List<SimpleContact> {
        val distinctLocations = getContactInOtherWay(ctx)!!
        return distinctLocations.sortedWith(Comparator { contact1, contact2 -> contact1.name.compareTo(contact2.name) })
    }
    fun getContacts(ctx: Context): List<SpeedDialContactModel>? {
        val list: MutableList<SpeedDialContactModel> = ArrayList()
        val contentResolver = ctx.contentResolver
        val cursor: Cursor? =
                contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
        if (cursor!!.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val cursorInfo: Cursor? = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(id),
                            null
                    )
                    val person: Uri =
                            ContentUris.withAppendedId(
                                    ContactsContract.Contacts.CONTENT_URI,
                                    id.toLong()
                            )
                    val pURI: Uri = Uri.withAppendedPath(
                            person,
                            ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
                    )

                    while (cursorInfo!!.moveToNext()) {
                        // info.setId(id)
                        var name: String = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        var mobileNumber: String = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        var photo: Uri = pURI
                        val info = SpeedDialContactModel(pURI, name, "", mobileNumber)
                        list.add(info)
                    }
                    cursorInfo.close()
                }
            }
            cursor.close()
        }
        return list
    }

    fun getContactInOtherWay(context: Context): ArrayList<SimpleContact>? {
        val phones: MutableMap<Long, ArrayList<String>> = HashMap()
        val cr: ContentResolver = context.contentResolver
        val list: ArrayList<SimpleContact> = ArrayList()
        var cur = cr.query(Phone.CONTENT_URI, arrayOf(Phone.CONTACT_ID, Phone.NUMBER, Phone.PHOTO_URI), null, null, null)
        while (cur != null && cur.moveToNext()) {
            val contactId = cur.getLong(0)
            val phone = cur.getString(1)
            // val photoUri = cur.getStringValue("photo_uri")
            var list: MutableList<String>
            if (phones.contains(contactId)) {
                list = phones[contactId]!!
            } else {
                list = ArrayList<String>()
                phones[contactId] = list
            }
            list.add(phone)
        }
        cur!!.close()

// Next query for all contacts, and use the phones mapping

// Next query for all contacts, and use the phones mapping
        cur = cr.query(ContactsContract.Contacts.CONTENT_URI, arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_URI), null, null, null)
        while (cur != null && cur.moveToNext()) {
            val id = cur.getLong(0)
            val name = cur.getString(1)
            val photoUri: String? = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_URI))
                    ?: ""
            if (name != null && phones[id] != null) {
                val contactPhones: ArrayList<String> = phones[id]!!
                val data = SimpleContact(0, id, name, photoUri!!, contactPhones)
                list.add(data)
            }

            // addContact(id, name, contactPhones)
        }
        return list
    }*/
}