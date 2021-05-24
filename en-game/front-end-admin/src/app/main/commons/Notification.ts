import Notiflix from "notiflix";


export class Notification {
    static showWaiting(title = 'Please wait...'): void {
        Notiflix.Loading.Circle(title);
    }

    static showSuccess(title, content = '', nameButton = 'click'): void {
        Notiflix.Loading.Remove();
        Notiflix.Report.Success(title, content, nameButton);
    }

    static showErrorStatus(err): void {
        Notiflix.Loading.Remove();
        Notiflix.Report.Failure(err.status + " " + err.error, 'Ok');
    }

    static showErrorMessage(err, message): void {
        Notiflix.Loading.Remove();
        Notiflix.Report.Failure(err, message);
    }

    static clearWaitNoMessage(): void {
        Notiflix.Loading.Remove();
    }

   
}
