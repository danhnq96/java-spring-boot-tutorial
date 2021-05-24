// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,
    hmr: false,
    base_api_url: "http://localhost",
    port: 8762,
    firebaseConfig: {
        apiKey: "AIzaSyCHICEykphvZKhKZGAACnHtKJ5lPBgQ3iE",
        authDomain: "endgame-eproject.firebaseapp.com",
        databaseURL: "https://endgame-eproject.firebaseio.com",
        projectId: "endgame-eproject",
        storageBucket: "endgame-eproject.appspot.com",
        messagingSenderId: "825973477244",
        appId: "1:825973477244:web:569e2c590f74d9bee7def9",
        measurementId: "G-W2467J8ZYJ"
    }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
