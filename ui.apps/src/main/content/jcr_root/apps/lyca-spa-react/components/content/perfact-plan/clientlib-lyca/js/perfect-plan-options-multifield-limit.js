(function (document, $) {
    "use strict";

    function toggleAddButton(multifield) {
        var maxItems = parseInt(multifield.data("max-items"), 10);
        var items = multifield[0].items.length;
        var addButton = multifield.find("[coral-multifield-add]");

        if (items >= maxItems) {
            addButton.hide();
        } else {
            addButton.show();
        }
    }

    $(document).on("foundation-contentloaded", function () {

        // OUTER multifield (Questions - max 3)
        $(".limit-questions").each(function () {
            var outerMf = $(this);

            toggleAddButton(outerMf);

            outerMf.on("coral-collection:add coral-collection:remove", function () {
                toggleAddButton(outerMf);
            });
        });

        // INNER multifield (Options - max 4 per question)
        function bindInnerMultifields(context) {
            $(context).find(".limit-options").each(function () {
                var innerMf = $(this);

                toggleAddButton(innerMf);

                innerMf.off("coral-collection:add coral-collection:remove"); // avoid duplicate binding

                innerMf.on("coral-collection:add coral-collection:remove", function () {
                    toggleAddButton(innerMf);
                });
            });
        }

        // Initial load
        bindInnerMultifields(document);

        // When a new question (composite multifield item) is added
        $(document).on("coral-collection:add", function (e) {
            bindInnerMultifields(e.target);
        });

    });

})(document, Granite.$);