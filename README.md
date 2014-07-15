# Sunrise

Sunrise is an "app snippet" that demonstrates loading images in a manner that respects the image's aspect ratio.

It uses Square's Picasso library.  Comments on StackOverflow complain of Picasso's fit() function not working properly.

I show how to use it, employing a custom view AspectRatioImageView to extend ImageView and override the onMeasure method.

The app also demonstrates the use of horizontal scroll views to wrap landscape photos.

See:

[MainActivity](https://github.com/boes-matt/Sunrise/blob/master/app/src/main/java/com/boes/sunrise/MainActivity.java)

[AspectRatioImageView](https://github.com/boes-matt/Sunrise/blob/master/app/src/main/java/com/boes/sunrise/AspectRatioImageView.java)

[activity_main.xml](https://github.com/boes-matt/Sunrise/blob/master/app/src/main/res/layout/activity_main.xml)
