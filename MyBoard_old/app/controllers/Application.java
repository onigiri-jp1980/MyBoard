package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Application extends Controller {
    public Result add(){
        return ok(views.html.dummy.render("新規投稿","投稿フォームに入力してください"));
    }
    public Result index(){
        return ok(views.html.dummy.render("新規投稿","投稿フォームに入力してください"));
    }
}
