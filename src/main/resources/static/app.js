$(document).ready(function() {

    var Image = Backbone.Model.extend({

        defaults: function() {
            return {
                id: -1,
                name: "nothing",
                data: "nothing",
                chunk_id: -1,
                annotated: false,
                annotation: ""
            };
        },

        persist: function(annotation) {
            this.save({annotated: true, annotation: annotation, annotator: person.id});
        }

    })

    var ImageList = Backbone.Collection.extend({

        model: Image,

        url: "/ws/images",

        comparator: "order"

    })

    var Person = Backbone.Model.extend ({

        url: "ws/people",

        defaults: function() {
            return {
                id: -1,
                name: "nothing"
            };
        },

        persist: function (name) {
            var response = this.save("name", name, {
                success: function(e) {
                    this.id = e.attributes.id;
                }
            });
        }
    })

    person = new Person;

    var PersonView = Backbone.View.extend ({

        el: $("#lifelog-person"),

        model: person,

        events: {
            "click .save": "login"
        },

        initialize: function() {
            this.listenTo(this, "login", this.login);
        },

        // Events //
        login: function() {
            var personName = $("#lifelog-name").val();
            this.model.persist(personName);
            App.render();
            $(this.el).hide();
        }

    })

    personView = new PersonView;

    var ImageView = Backbone.View.extend ({


        tagName:  "div",

        template: _.template($('#item-template').html()),

        events: {
            "click .save": "save"
        },

        initialize: function() {
            this.listenTo(this.model, 'change', this.render);
            this.listenTo(this.model, 'destroy', this.remove);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.input = this.$('.edit');
            return this;
        },

        // Events //
        save: function() {
            var annotation = $(this.$(".annotation")[0]).val();
            this.model.persist(annotation);
            $(this.el).hide();
        }

    })

    var Images = new ImageList;

    var AppView = Backbone.View.extend({

        el: $("#lifelog-app"),

        initialize: function() {


            this.listenTo(Images, 'add', this.addOne);

            this.main = $('#main');
            this.main.hide();

            Images.fetch();
        },

        render: function() {
            this.main.show();
        },

        addOne: function(image) {
            var view = new ImageView({model: image});
            this.$("#lifelog-image-list").append(view.render().el);
        },

        addAll: function() {
            Images.each(this.addOne, this);
        },

    })

    var App = new AppView;

});