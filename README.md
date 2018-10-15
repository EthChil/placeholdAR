# placeholdAR

MHacks 11 submission combining wayfair API and StockX API to create an interactive shop space. Video demo:

https://youtu.be/a0DnSlCX2Fw

## Inspiration


## What it does
placeholdAR allows anyone to create a virtual shopping experience within the comfort of their own. 

## How we built it
We used Android-Studio to develop a Java implementation of ARcore by Google, an Augmented Reality service. This was in conjunction with StockX's API and WayFair's API to generate furniture and place products on top of the furniture. We used RESTful calls to grab extra information on the StockX products aswell.

## Challenges we ran into
First, we had to get our environments setup which took a while, but had some issues around the SDK's and compiling with the gradle build. Each step was a challenge for us that we had to overcome due to the steep learning curve of ARcore. Getting a 2D image to show in space took several hours, 3D a few more. Then getting them to display on a vertical plane over the horizontal plane was another issue we had for the t-shirts. But once we did that they showed up always normal which is not how we would like to view a T-shirt, so we had to use advanced packages to change the angle of it on instantiation. We wanted the API's to run during runtime for the user to be able to choose the product they like from StockX's catalog. Lastly we had lots of issues with Java's HTTPS REST call api's aswell towards the end.

## Accomplishments that we're proud of
With none of us having any knowledge of Augmented Reality before, how to code it, the different options availible, or even the environment to use, in 20 hours we were able to get custom images in 2D and 3D placed in 3D space. We had multiplayer working with another version of the app but had to let it go due to the change in idea, buit we were able to interact with eachother. The best part for us is that this technology is so new from Google that the docs and support for it are very limited, but we were still able to get most of the features we wanted.

## What we learned
We learrned tremendous amounts about ARcore. It is completely different to any type of program we have ever made and pushed our skillsets immensly. By the four of us each working on a seperate part of the program we had to communicate to ensure we did not have merge errors or fatal design flaws. Lastly we learned how to work with several API's effeciently and use RESTful calls to fill in the missing endpoints the API's could not provide us with.

## What's next for placeholdAR
We had many plans for placeholdAR; multiplayer experience, payment API's, .OBJ generation from StockX 360 imaging previews, 360 trying on of clothes, etc. Theses updates will be worked as we find time since we really enjoyed the uniqueness AR provides to a solution to a common problem. With the addition of multiplayer through firebase storage of anchor points, ARcore becomes crossplatform just like that.
