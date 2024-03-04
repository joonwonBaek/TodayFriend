import { createBrowserRouter } from 'react-router-dom';

import { HomePage } from '@/pages/HomePage/HomePage';
import { LocationPage } from '@/pages/LocationPage/LocationPage';
import { MainPage } from '@/pages/MainPage/MainPage';
import { MyProfilePage } from '@/pages/MyProfilePage/MyProfilePage';
import { SignInPage } from '@/pages/SignInPage/SignInPage';
import { SignUpPage } from '@/pages/SignUpPage/SignUpPage';

import { PATH } from './path';

export const router = createBrowserRouter([
  {
    path: PATH.ROOT,
    element: <MainPage />,
    children: [
      {
        path: PATH.ROOT,
        element: <HomePage />,
      },
      {
        path: PATH.SIGNIN,
        element: <SignInPage />,
      },
      {
        path: PATH.SIGNUP,
        element: <SignUpPage />,
      },
      {
        path: PATH.MYPROFILE,
        element: <MyProfilePage />,
      },
      {
        path: PATH.LOCATION,
        element: <LocationPage />,
      },
    ],
  },
]);
