# Stahp Client

Server for my Stahp game.

The game (both client and server) is not finished, but should be runnable.

This project is published just to be archived as reference. Don't expect great pratices from it.

## API documentation

This is just a random sketch I made of the API, it's not 100% compatible with the implementation.

### REST end points

* /match
  * POST - Create a new match
  * GET - Get your match list
* /match/{match}
  * GET - Get match info
  * POST - Apply (or start) a match
* /match/{match}/words (?)
  * GET - Get the match's word list
  * PUT - Send a player's word list
  * POST - Get the for a word list
* /match/{match}/words/{player} (?)
  * GET - Get a player's word list
* /player
  * POST - Create a new player
  * GET - Get current player's profile
  * PUT - Update player information
* /player/{player}
  * GET - Get a player's profile

### Data types

Player
- id
- username

Game
- public
- status (created, started, finished)

WordQuery
- letter
- topic

WordResponse
- word
- unique
- score

# Reference material

GenericDAO - https://code.google.com/p/hibernate-generic-dao/
Jersey - https://jersey.java.net/

The RESTful CookBook - http://restcookbook.com/HTTP%20Methods/put-vs-post/

# License

The project, both server and client, is released under MIT License.
