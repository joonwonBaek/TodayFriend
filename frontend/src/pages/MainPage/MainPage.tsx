import { Outlet } from 'react-router-dom';

import BottomNavBar from '@/components/BottomNavBar';

export const MainPage = () => {
  return (
    <>
      <Outlet />
      <BottomNavBar />
    </>
  );
};
