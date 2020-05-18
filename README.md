# Paytm Insider Android developer task

This repo is a task for android developer position at Paytm Insider. It shows the list of events in your city.


# Getting started

Clone this repo or download repo and import it into Android studio or IntelliJ Idea IDE. 

Repo link:
[https://github.com/cybertronjc/PaytmInsider](https://github.com/cybertronjc/PaytmInsider)

minimum sdk version = 16
target sdk version = 29
build tool version = 29.0.3


## Dependencies

Retrofit: Http based networking library
Glide: Image loading library
Android Architecture component library
Facebook shimmer : Shimmer effect on views

## Architecture
MVVM architecture is used in this application which has three components.
-   The  _View_  — that informs the ViewModel about the user’s actions
-   The  _ViewModel_  — exposes streams of data relevant to the View
-   The  _DataModel_  — abstracts the data source. The ViewModel works with the DataModel to get and save the data.
- 

## Single event view

To get details of a single event view click on any event and it will ask you to open the event link in your browser.

For details of single article click on any item of Image Carousel and it will ask you to open the article link in your browser.

## Dark theme
Dark theme is also implemented in the app. Dark themes reduce the luminance emitted by device screens, while still meeting minimum color contrast ratios.

It can be turned on by switch on the top right corner. 

## Running the tests
RecyclerViewTest is a test  implemented for onclick item. Click on run button left to the function.
