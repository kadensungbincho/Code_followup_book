package ch1_safty

/*
    Not- null assertion !! is a lazy option.
    It throws a generic exception that explains nothing.
    It is also short and simple, which also makes it easy to
    abuse or misuse.

    explicit errors say much more than generic NPE and they should be nearly always preferred.

    we should avoid using the not-null assertion !!
 */
/*
    lateinit vs nullable

    - we do not need to unpack property every time to not-nullable
    - we can easily make it nullable in the future if we need to use null to
        indicate something meaningful
    - one property is initialized, it cannot get back to an uninitialized state

 */
