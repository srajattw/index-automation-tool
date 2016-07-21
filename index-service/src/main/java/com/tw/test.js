
var counter = 0;

var add = (function () {
    return counter += 1;
});

add();
add();
add();

console.log(counter)