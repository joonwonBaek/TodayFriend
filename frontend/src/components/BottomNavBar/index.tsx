import { Link } from 'react-router-dom';

import { PATH } from '@/routes/path';

import Icon from '../Common/Icon';

type pathType = typeof PATH.ROOT | typeof PATH.SIGNIN | typeof PATH.SIGNUP;

interface NavItemProps {
  path: pathType;
  icon: JSX.Element;
}

const BottomNavBar = () => {
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
      path: PATH.SIGNUP,
      icon: <Icon name="location_on" isFill={false} />,
    },
    {
      path: PATH.MYPROFILE,
      icon: <Icon name="account_circle" isFill={false} />,
    },
  ];
  return (
    <div>
      {navItems.map(({ path, icon }) => (
        <Link key={path} to={path}>
          {icon}
        </Link>
      ))}
    </div>
  );
};

export default BottomNavBar;
