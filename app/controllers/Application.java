package controllers;

import play.api.i18n.MessagesApi;
import play.data.*;
import play.mvc.Result;
import play.mvc.*;
import models.*;
import play.data.FormFactory;


import javax.inject.Inject;
import java.util.List;


public class Application extends Controller {
    private final FormFactory formFactory;
    private final MessagesApi messagesApi;
    public Form<MyBoard> postForm;

    @Inject
    public Application(FormFactory formFactory, MessagesApi messagesApi) {
        this.formFactory = formFactory;
        this.messagesApi = messagesApi;
        this.postForm = formFactory.form(MyBoard.class);
    }

    public Result index(Http.Request request) {
        Form<MyBoard> post = formFactory.form(MyBoard.class).bindFromRequest(request);
        List<MyBoard> posts = MyBoard.finder.all();
        for(MyBoard _post: posts){
            _post.setMessage(unEscapeCrLr(_post.getMessage()));
        }

        return ok(views.html.index.render("投稿一覧", this.postForm, posts, request, messagesApi.preferred(request)));
    }
    public Result edit(Integer _id, Http.Request request){
        MyBoard mb = MyBoard.finder.byId(_id);
        return ok(views.html.edit.render("新規投稿", this.postForm.fill(mb), mb, request, messagesApi.preferred(request)));
    }

    public Result add(Http.Request request){
        Form<MyBoard> post = formFactory.form(MyBoard.class).bindFromRequest(request);
        MyBoard mb = post.get();

        if (!post.hasErrors()) {
            mb.setMessage(escapeCrLr(sanitize(mb.getMessage())));
            mb.setName(post.get().getName());
            mb.setEmail(post.get().getEmail());
            mb.setDeleted(false);
            mb.insert();
        }
        return redirect("/");
    }
    public Result delete(Integer _id, Http.Request request){
        MyBoard mb = new MyBoard();
        mb.setId(_id);
        mb.delete();
        return redirect("/");
    }
    public Result update(Integer _id, Http.Request request){
        Form<MyBoard> post = formFactory.form(MyBoard.class).bindFromRequest(request);
        MyBoard mb = post.get();
        if (!post.hasErrors()) {
            mb.setId(_id);
            mb.setMessage(escapeCrLr(sanitize(mb.getMessage())));
            mb.deleted = false;
            mb.update();
        }
        return redirect("/");

    }

    //エスケープした本文の改行を復元(View側でbrタグに)
    private String unEscapeCrLr (String _str){
        String regex = "\\\\r\\\\n|\\\\r|\\\\n";
        return _str.replaceAll(regex, "\r\n");
    }

    //本文の改行をエスケープ
    private String escapeCrLr (String _str){
        String regex = "\r\n|\r|\n";
        return _str.replaceAll(regex, "\\\\r\\\\n");
    }
    //サニタイズ
    private String sanitize(String _str){
        if (null == _str || "".equals(_str)) {
            return _str;
        }
        _str = _str.replaceAll("&", "&amp;");
        _str = _str.replaceAll("<", "&lt;");
        _str = _str.replaceAll(">", "&gt;");
        _str = _str.replaceAll("\"", "&quot;");
        _str = _str.replaceAll("'", "&#39;");
        return _str;
    }



}