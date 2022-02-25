package edu.cnm.deepdive.codebreaker;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.codebreaker.service.CodebreakerDatabase;
import edu.cnm.deepdive.codebreaker.service.GoogleSignInService;

public class CodebreakerApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    CodebreakerDatabase.setContext(this);
    GoogleSignInService.setContext(this);
    // TODO Initialize repositories that need an app-level context.
//    GameRepository repository = new GameRepository(this);
//    repository
//      .startGame("XYZ", 3)
//      .flatMap((game) -> repository.submitGuess(game, "ZZZ"))
//        .subscribe();
    // Initialize database.
    // Initialize repositories that need an app-level context
    // etc.
  }

}
