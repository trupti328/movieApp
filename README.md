# movieApp
This is an Android app that shows movie details.
Users can open the app, browse a list of movies (fetched from an API), and click to see full details about a movie.
The app is built using:

Kotlin

Retrofit (for API calls)

ViewModel (MVVM Architecture)

RecyclerView

Intent for passing data between activities

Jetpack compose

XML Layouts

🔄 Project Flow
MainActivity calls the API using Retrofit and shows a list of movies in a RecyclerView.

When a user clicks on a movie, an Intent opens the DetailsActivity.

DetailsActivity shows full details of the selected movie.

The app uses MovieDetailViewModel to manage data for the detail screen.

❌ Problems I Faced
1. Error While Fetching API Data
I got errors while calling the API.

Problem happened when I tried to fetch data using the movie id on the second screen.

2. Movie id Was Showing Default Value
On the second screen, the movie id was coming as 0 instead of the correct value.

I didn’t know how to properly get the id from the Intent.

3. Didn't Know How to Use Koin (Dependency Injection)
I tried to use Koin for dependency injection.

But I didn’t know how to set it up correctly.
I learned it from Youtube and applied it.


✅ How I Fixed Things
Made sure to pass the correct movie id using Intent

Created API service and other objects manually and passed them as needed.

Now, the app correctly fetches and displays data without crashing.

💡 What I Learned
Always check if Intent values are passed and received correctly.

Retrofit is simple to use for making API calls.

When facing app crashes, checking logs and error messages helps a lot.

✅ Future Improvements

Add error handling if the API call fails.

Add search functionality to find a movie by name.

Improve the UI with Material Design Components.
