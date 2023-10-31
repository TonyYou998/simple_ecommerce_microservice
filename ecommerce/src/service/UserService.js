import Keycloak from "keycloak-js";
// let initOptions={
//     url: 'http://localhost:7080/',
//         realm: 'master',
//         clientId: 'react-ac',
//         onLoad: 'check-sso', // login-required
//         KeycloakResponseType: 'code',
//       }

let initOptions = {
  url: 'http://localhost:7080/',
  realm: 'master',
  clientId: 'react-ac',
  onLoad: 'check-sso', // check-sso | login-required
  KeycloakResponseType: 'code',

  // silentCheckSsoRedirectUri: (window.location.origin + "/silent-check-sso.html")
}
// const kc=new Keycloak(initOptions);
let kc = new Keycloak(initOptions);
const initKeycloak = (onAuthenticatedCallback) => {
  kc.init({
    onLoad: 'check-sso',
    silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html',
    pkceMethod: 'S256',
    KeycloakResponseType:'code'
  })
    .then((authenticated) => {
      if (!authenticated) {
        console.log("user is not authenticated..!");
      }
      onAuthenticatedCallback();
    })
    .catch(console.error);
  // kc.init({
  //   onLoad: initOptions.onLoad,
  //   KeycloakResponseType: 'code',
  //   silentCheckSsoRedirectUri: window.location.origin + "/silent-check-sso.html", checkLoginIframe: false,
  //   pkceMethod: 'S256'
  // }).then((auth) => {
  //   if (!auth) {
  //     window.location.reload();
  //   } else {
  //     console.info("Authenticated");
  //     console.log('auth', auth)
  //     console.log('Keycloak', kc)
  //     kc.onTokenExpired = () => {
  //       console.log('token expired')
  //     }
  //   }
  // }, () => {
  //   console.error("Authenticated Failed");
  // });
};
const doLogin = kc.login;

const doLogout = kc.logout;

const getToken = () => kc.token;

const getTokenParsed = () => kc.tokenParsed;

const isLoggedIn = () => {
    return kc.token !== null && kc.token !== undefined;
  }

const updateToken = (successCallback) =>
  kc.updateToken(5)
    .then(successCallback)
    .catch(doLogin);

const getUsername = () => kc.tokenParsed?.preferred_username;

const hasRole = (roles) => roles.some((role) => kc.hasRealmRole(role));

const UserService = {
  initKeycloak,
  doLogin,
  doLogout,
  isLoggedIn,
  getToken,
  getTokenParsed,
  updateToken,
  getUsername,
  hasRole,
};
export default UserService;