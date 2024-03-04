import { useLocation } from 'react-router-dom';

import { PATH } from '@/routes/path';

import Icon from '../Common/Icon';
import { BottomNavBarWrapper, LinkWrapper } from './style';

type pathType = typeof PATH.ROOT | typeof PATH.SIGNIN | typeof PATH.SIGNUP;

interface NavItemProps {
  path: pathType;
  icon: JSX.Element;
}

const BottomNavBar = () => {
  const location = useLocation();
  const currentPathName = location.pathname;

  const isPathActive = (path: pathType) => {
    return currentPathName === path;
  };

  const navItems: NavItemProps[] = [
    {
      path: PATH.ROOT,
      icon: <Icon name="person_search" isFill={false} />,
    },
    {
      path: PATH.SIGNIN,
      icon: <Icon name="sms" isFill={false} />,
    },
    {
      path: PATH.LOCATION,
      icon: <Icon name="location_on" isFill={false} />,
    },
    {
      path: PATH.MYPROFILE,
      icon: <Icon name="account_circle" isFill={false} />,
    },
  ];
  return (
    <BottomNavBarWrapper>
      {navItems.map(({ path, icon }) => (
        <LinkWrapper
          key={path}
          to={path}
          active={isPathActive(path).toString()}>
          {icon}
        </LinkWrapper>
      ))}
    </BottomNavBarWrapper>
  );
};

export default BottomNavBar;
