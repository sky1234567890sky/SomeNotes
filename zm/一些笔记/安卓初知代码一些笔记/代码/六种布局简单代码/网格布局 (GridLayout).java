<GridLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:columnCount="4"
    android:orientation="horizontal"
    android:rowCount="5" >

    <Button android:text="1" />

    <Button android:text="2" />

    <Button android:text="3" />

    <Button android:text="/" />

    <Button android:text="4" />

    <Button android:text="5" />

    <Button android:text="6" />

    <Button android:text="*" />

    <Button android:text="7" />

    <Button android:text="8" />

    <Button android:text="9" />

    <Button android:text="-" />

    <Button
        android:layout_columnSpan="2"
        android:layout_gravity="fill"
        android:text="0" />

    <Button android:text="." />

    <Button
        android:layout_gravity="fill"
        android:layout_rowSpan="2"
        android:text="+" />

    <Button
        android:layout_gravity="fill"
        android:layout_columnSpan="3"
        android:text="=" />

</GridLayout>