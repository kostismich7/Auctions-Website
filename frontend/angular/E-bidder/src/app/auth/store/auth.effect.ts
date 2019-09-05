import {Injectable} from "@angular/core";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {AuthActionTypes, UserLoginAction, UserTryLoginAction,} from "./auth.actions";
import {map} from "rxjs/operators";


@Injectable()

export class AuthEffect {

  constructor(private actions$: Actions){}

  @Effect()
  authLoginIn = this.actions$.pipe(
    ofType(AuthActionTypes.USER_TRY_LOGIN),
    map((action:UserTryLoginAction) => {
      const ResponseData = action.payload;
      //get user status in response body
      return new UserLoginAction({userStatus: 'user',token:ResponseData.Data.idToken})
    })
  )

}