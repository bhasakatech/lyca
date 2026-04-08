console.log("This is multifield limit");
(function (document, $) {
    "use strict";

    var SELECTOR = ".limit-appstore";

    console.log("Max items :: ");
    
    function toggleAddButton(multifield) {
        var maxItems = multifield.data("max-items") || 3;
        var items = multifield[0].items.length;
        var addButton = multifield.find("[coral-multifield-add]");

        console.log("Max items :: "+maxItems+", items :: "+items);
        
        if (items >= maxItems) {
            addButton.hide();
        } else {
            addButton.show();
        }
    }

    $(document).on("foundation-contentloaded", function () {

        $(SELECTOR).each(function () {
            var multifield = $(this);

            toggleAddButton(multifield);

            multifield.on("coral-collection:add", function () {
                toggleAddButton(multifield);
            });

            multifield.on("coral-collection:remove", function () {
                toggleAddButton(multifield);
            });
        });
    });

})(document, Granite.$);